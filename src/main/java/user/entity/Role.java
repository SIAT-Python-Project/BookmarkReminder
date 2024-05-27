package user.entity;

import common.util.convert.AbstractEnumNameConverter;
import common.util.convert.EnumName;

public enum Role implements EnumName<String> {
    ADMIN, USER;

    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Role, String> {
        public Converter() {
            super(Role.class);
        }
    }
}
