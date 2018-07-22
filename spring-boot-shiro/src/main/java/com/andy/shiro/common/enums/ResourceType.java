package com.andy.shiro.common.enums;

public enum ResourceType implements BaseEnum<ResourceType, Integer> {

    MENU(1, "菜单"), BUTTON(2, "按钮");

    private Integer code;

    private String name;

    ResourceType(Integer code, String name) {
        this.name = name;
        this.code = code;
    }



//    public Integer code(ResourceType resourceType) {
//        for (ResourceType type : ResourceType.values()) {
//            if (type.equals(resourceType)) {
//                return type.getCode();
//            }
//        }
//        return null;
//    }
//
//    public String name(ResourceType resourceType) {
//        for (ResourceType type : ResourceType.values()) {
//            if (type.equals(resourceType)) {
//                return type.getName();
//            }
//        }
//        return null;
//    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    @Override
    public Integer code() {
        return this.getCode();
    }
}
