package mk.finki.ukim.mk.lab.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.finki.ukim.mk.lab.model.UserFullName;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullName, String> {
    private static final String SEPARATOR = "";
    @Override
    public String convertToDatabaseColumn(UserFullName userFullName){
        if (userFullName==null) return null;
        StringBuilder sb = new StringBuilder();
        if (userFullName.getName()!=null&&!userFullName.getName().isEmpty()){
            sb.append(userFullName.getName());
            sb.append(SEPARATOR);
        }
        if (userFullName.getName()!=null&&!userFullName.getSurname().isEmpty()){
            sb.append(userFullName.getSurname());
        }
        return sb.toString();
    }

    @Override
    public UserFullName convertToEntityAttribute(String s) {
        if (s==null||s.isEmpty()) return null;
        String[] parts = s.split(SEPARATOR);
        if (parts==null||parts.length==0) return null;
        UserFullName userFullName = new UserFullName();
        String part1 = !parts[0].isEmpty()?parts[0]:null;
        if (s.contains(SEPARATOR)){
            userFullName.setSurname(part1);
            if (parts.length>=2&&parts[1]!=null && !parts[1].isEmpty())
                userFullName.setName(parts[1]);
            else userFullName.setName(part1);
        }
        return userFullName;
    }
}