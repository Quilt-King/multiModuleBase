package cn.zl.boot.zlapi.enums.errorcode;

import cn.zl.boot.common.core.IntArrayValuable;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码的类型枚举
 *
 * @author dylan
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeTypeEnum implements IntArrayValuable {

    /**
     * 自动生成
     */
    AUTO_GENERATION(1),
    /**
     * 手动编辑
     */
    MANUAL_OPERATION(2);

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(ErrorCodeTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
