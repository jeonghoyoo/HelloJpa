package entityMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum RoleType {
    USER ("01"),
    ADMIN ("02");

    private String roleCode;
    private static Map<String, RoleType> classNameMap = Arrays.stream(values())
                                                              .collect(Collectors.toMap(RoleType::getRoleCode,
                                                                        Function.identity()));
    RoleType(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public static RoleType getRoleTypeBy(String roleCode) {
        return classNameMap.get(roleCode);
    }
}
