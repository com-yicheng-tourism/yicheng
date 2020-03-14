package com.yicheng.tourism.base.enums;

/**
 * 返回结果状态。
 */
public enum StatusEnum {

    SUCCESS("000_0000_0000", "执行成功"),
    FAIL("000_0000_0001", "执行失败"),
    NO_PERMISSION("000_0000_0002", "没有权限执行"),
    NO_DATA("000_0000_0003", "查询不到对应数据"),
    ERROR_PARAM("000_0000_0004", "参数错误"),
    EXCEPT_UNKNOW("000_0000_0011", "未知异常"),
    EXCEPT_DB_OPERATION("000_0000_0012", "数据库操作异常"),
    EXCEPT_OPERATION("000_0000_0013", "操作异常"),
    EXCEPT_SERVER("000_0000_0014", "服务异常"),
    NO_EXISTS_USER("000_0000_0015", "用户不存在"),
    NO_EXISTS_DATA("000_0000_0016", "数据不存在"),
    ERROR_FILE_FORMAT("000_0000_0017", "上传文件格式不正确"),
    USED_USERNAME("000_0000_0018", "用户名已被使用"),
    ERROR_PHONE_FORMAT("000_0000_0019", "该手机号码不符合规则"),
    USED_PHONE("000_0000_0020", "该联系电话已被使用"),
    USED_EMAIL("000_0000_0021", "该邮箱已被使用"),
    ERROR_DATA_IN_FILE_IMPORT("000_0000_0022", "文件导入存在失败数据"),


    VALIDATION_FAIL("003_0001_0001", "参数校验失败"),
    PARAM_IS_NULL("003_0001_0002", "参数为空"),
    NO_SUCH_USER_ACCOUNT("003_0001_0003", "无此用户"),
    MOBILE_IS_EMPTY("003_0001_0004", "手机号为空"),
    MOBILE_PATTERN_ERROR("003_0001_0005", "手机号格式不正确"),
    TICKET_NOT_EXSIT("003_0001_0006", "卷信息不存在"),
    VERIFY_CODE_IS_INVALID("003_0001_0007", "验证码超时，请重新获取"),
    USER_NOT_LOGIN("003_0001_0008", "用户未登陆"),
    USER_NOT_EXSIT("003_0001_0009", "用户不存在"),
    CONPON_USED_OR_NOT_EXSIT("003_0001_0010", "优惠卷已使用或不存在"),
    VERIFY_CODE_IS_ERROR("003_0001_0011", "验证码错误，请重新输入"),
    USER_RIGHTS_IS_TAKE("003_0001_0012", "权益已生效"),
    USER_RIGHTS_NOT_TAKE("003_0001_0013", "权益未生效"),
    INFORMATION_NOT_EXIST("003_0001_0014", "信息不存在"),
    VALIDATE_TIME_NOT_NULL("003_0001_0015", "权益有效时长不能为空"),
    CAN_NOT_REMOVE("003_0001_0016", "当前权益已有用户使用，无法删除"),
    HEADER_USERID_NULL("003_0001_0017", "用户不存在"),
    HEADER_OPERATORID_NOT_NULL("003_0001_0018", "操作员不能为空"),
    HEADER_EID_NOT_NULL("003_0001_0019", "eId不能为空"),
    HEADER_REQUESTID_NOT_NULL("003_0001_0020", "请求流水号不能为空"),
    PRODUCT_INVENTORY_SHORTAGE("003_0001_0021", "产品库存不足"),
    PRODUT_OUT_OF_DATE("003_0001_0021", "产品已过有效期"),
    PRODUCT_EXCLUSION("003_0001_0022", "产品互斥"),
    SHOP_NOT_PRODUCT("003_0001_0023", "未在店铺下找到商品"),

    FLOWPRODUCT_MAX_NUM("003_0001_0023", "产品最大数量不能超过"),


    /**
     * cmp 接口返回码   注掉的cmp返回码勿删
     */

    CMP_REQUIRED_SUCCESS("000_0000_0000", "请求成功"),
    CMP_REQUIRED_MUTEX("002_0010_0220", "套餐互斥"),
    CMP_REQUIRED_YMUTEX("002_0010_0221", "套餐不互斥"),
    CMP_PKG_NUMBER_ERROR("4000_10_281", "套餐数量不能超过10个"),

    /*CMP_REQUIRED_MUTEX("4000_10_220","套餐互斥"),
    CMP_REQUIRED_YMUTEX("4000_10_221","套餐不互斥"),
    CMP_REQUIRED_SUCCESS("4000_00_0","请求成功");//套餐不互斥
*/
    /**
     * cpsp接口返回码
     */
    CPSP_SUCCESS("004_0003_0000", "成功"),

    ;

    /**
     * 枚举值码
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * 构建一个 StatusEnum 。
     *
     * @param code    枚举值码。
     * @param message 枚举描述。
     */
    private StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 得到枚举值码。
     *
     * @return 枚举值码。
     */
    public String getCode() {
        return code;
    }

    /**
     * 得到枚举描述。
     *
     * @return 枚举描述。
     */
    public String getMessage() {
        return message;
    }

    /**
     * 得到枚举值码。
     *
     * @return 枚举值码。
     */
    public String code() {
        return code;
    }

    /**
     * 得到枚举描述。
     *
     * @return 枚举描述。
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 StatusEnum 。
     */
    public static StatusEnum findStatus(String code) {
        for (StatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ResultInfo StatusEnum not legal:" + code);
    }


}
