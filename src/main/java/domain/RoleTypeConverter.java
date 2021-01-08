package domain;

import entityMapping.RoleType;

import javax.persistence.AttributeConverter;

public class RoleTypeConverter implements AttributeConverter<RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType roleType) {
        return roleType.getRoleCode();
    }

    @Override
    public RoleType convertToEntityAttribute(String roleTypeCode) {
        return RoleType.getRoleTypeBy(roleTypeCode);
    }
}
