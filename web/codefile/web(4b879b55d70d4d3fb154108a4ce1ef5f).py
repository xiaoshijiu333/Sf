from flask import *
import tensorflow as tf
import numpy as np
import PIL.Image as Image
import io
def recognize(img, pb_file_path):
    classes = {0:'AKIEC', 1:'BCC', 2:'BKL', 3:'DF', 4:'MEL', 5:'NV', 6:'VASC'}
    with tf.Graph().as_default():
        output_graph_def = tf.GraphDef()

        with open(pb_file_path, "rb") as f:
            output_graph_def.ParseFromString(f.read())
            tensors = tf.import_graph_def(output_graph_def, name="")
            print(tensors)

        with tf.Session() as sess:
            init = tf.global_variables_initializer()
            sess.run(init)

            op = sess.graph.get_operations()

            for m in op:
                print(m.values())

            input_x = sess.graph.get_tensor_by_name("main_input:0")  # 具体名称看上一段代码的input.name
            print(input_x)

            out_softmax = sess.graph.get_tensor_by_name("main_output/Softmax:0")  # 具体名称看上一段代码的output.name

            print(out_softmax)

            # img = Image.open(jpg_path)
            img = img.convert('RGB')
            img = img.resize((299, 299), Image.NEAREST)
            img = np.array(img)
            img = img.reshape((1, 299, 299, 3)) / 255.0
            img_out_softmax = sess.run(out_softmax,
                                       feed_dict={input_x: img})

            print("img_out_softmax:", img_out_softmax)
            prediction_labels = np.argmax(img_out_softmax, axis=1)
            print("label:", classes[prediction_labels[0]])
            return classes[prediction_labels[0]]

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def service():
    pb_path = './tensorflow_model/tensor_model.pb'
    if request.method == 'POST':
        input_x = request.files.get('image').read()
        input_x = io.BytesIO(input_x)
        input_x= Image.open(input_x)
        g.output = recognize(input_x, pb_path)
        return render_template("page.html", output=g.output)
    return render_template("page.html")

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000, threaded=True)

# from flask import Flask
# from flask import request
#
# app = Flask(__name__)
#
# @app.route('/', methods=['GET', 'POST'])
# def home():
#     return '<h1>Home</h1>'
#
# @app.route('/signin', methods=['GET'])
# def signin_form():
#     return '''<form action="/signin" method="post">
#               <p><input name="username"></p>
#               <p><input name="password" type="password"></p>
#               <p><button type="submit">Sign In</button></p>
#               </form>'''
#
# @app.route('/signin', methods=['POST'])
# def signin():
#     # 需要从request对象读取表单内容：
#     if request.form['username']=='admin' and request.form['password']=='password':
#         return '<h3>Hello, admin!</h3>'
#     return '<h3>Bad username or password.</h3>'
#
# if __name__ == '__main__':
#     app.run()