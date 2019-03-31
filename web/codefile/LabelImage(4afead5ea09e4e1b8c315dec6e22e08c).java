package Services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import javax.imageio.ImageIO;

public class LabelImage {

    public String  Image(String imageFile) throws IOException {

        String path = LabelImage.class.getClassLoader().getResource("tensor_model.pb").getPath();
        //必须要截取一下路径前面的/
        String subpath = path.substring(1);

        byte[] graphDef = readAllBytesOrExit(Paths.get(subpath));

        String[] catagory = {"AKIEC", "BCC", "BKL", "DF", "MEL", "NV", "VASC"};

        try (Tensor<Float> floatTensor = constructAndExecuteGraphToNormalizeImage(imageFile)) {
            float[] labelProbabilities = executeInceptionGraph(graphDef, floatTensor);
            int bestLabelIdx = maxIndex(labelProbabilities);
            String cata = catagory[bestLabelIdx];
            return String.format("The largest probability: (%s : %.8f%%)",
                    cata, labelProbabilities[bestLabelIdx] * 100f);
        }
    }

    private static Tensor<Float> constructAndExecuteGraphToNormalizeImage(String path) throws IOException {
        //加载图像
        BufferedImage bimg = ImageIO.read(new File(path));
        //重塑图像大小
        BufferedImage tag = new BufferedImage(299, 299, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(bimg, 0, 0, 299, 299, null);
        //定义矩阵 三维数组——>四维，每个像素点除以225
        float[][][][] data = new float[1][299][299][3];
        //获取RGB三个数，赋值给矩阵
        for (int i = 0; i < 299; i++) {
            for (int j = 0; j < 299; j++) {
                int rgb = tag.getRGB(i, j);
                data[0][i][j][0] = ((rgb & 0xff0000) >> 16) / 255.0f;
                data[0][i][j][1] = ((rgb & 0xff00) >> 8) / 255.0f;
                data[0][i][j][2] = (rgb & 0xff)/ 255.0f;
            }
        }
        Tensor<Float> tensor = (Tensor<Float>) Tensor.create(data);
        return tensor;
    }

    private static float[]
    executeInceptionGraph(byte[] graphDef, Tensor<Float> image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session s = new Session(g);
                 // Generally, there may be multiple output tensors, all of them must be closed to prevent resource leaks.
                 Tensor<Float> result =
                         s.runner().feed("main_input:0", image).fetch("main_output/Softmax:0").run().get(0).expect(Float.class)) {
                final long[] rshape = result.shape();
                if (result.numDimensions() != 2 || rshape[0] != 1) {
                    throw new RuntimeException(
                            String.format(
                                    "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                                    Arrays.toString(rshape)));
                }
                int nlabels = (int) rshape[1];
                return result.copyTo(new float[1][nlabels])[0];
            }
        }
    }

    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}