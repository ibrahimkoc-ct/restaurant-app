package Temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDtoConverter {
    public static List<UsersDTO> userListToUserDTOList(List<Users> userList){
        List<UsersDTO> usersDTO = new ArrayList<>();
        for(Users users :userList){
            UsersDTO user = new UsersDTO();
            user.setEnabled(users.getEnabled());
            user.setPassword(users.getPassword());
            user.setUsername(users.getUsername());
            usersDTO.add(user);
        }
        return usersDTO;
    }

    public static Users usersDTOaddUsers(UsersDTO usersDTO){
        Users user = new Users();
        user.setEnabled(usersDTO.getEnabled());
        user.setPassword(usersDTO.getPassword());
        user.setUsername(usersDTO.getUsername());
        return user;
    }
    public static String userDTOdeleteUsers(String username){
        Users user= new Users();
        user.setUsername(username);
        return user.getUsername();
    }
    public static UsersDTO userDTOgetByID(Optional<Users> dtoList){
        UsersDTO dto= new UsersDTO();
        dto.setUsername(dtoList.get().getUsername());
        dto.setPassword(dtoList.get().getPassword());
        dto.setEnabled(dtoList.get().getEnabled());
        return dto;
    }


}
