package cn.codeyang.yang.framework.transmission.rpc;

public class TagProvider {

    private Long tag;

    public TagProvider() {
        tag = 0L;
    }
    public Long nextTag() {
        return ++tag;
    }

}
