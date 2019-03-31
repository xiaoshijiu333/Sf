package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class PageBean<T> {
    //��ǰҳ
    private Integer currentPage;
    //��ҳ��
    private Integer totalPage;
    //�ܼ�¼��
    private Integer totalCount;
    //һҳ��ʾ��������
    private Integer pageSize;
    //��ǰ���ݿ��ѯ�ĽǱ�
    private Integer index;
    //��ǰҳ����
    private List<T> pageList;


    //���û�����õ�ǰҳ��Ĭ�ϵ�Ϊ��һҳ
    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    //��������ݿ��ѯ�ĽǱ�
    public Integer getIndex() {
        return (currentPage-1)*pageSize;
    }

    //Ĭ��һҳ��ʾ7������
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 5;
        }
        this.pageSize = pageSize;
    }

    //������ҳ��
    public Integer getTotalPage() {
        double ceil = Math.ceil(totalCount * 1.0 / pageSize);
        return (int)ceil;
    }

}