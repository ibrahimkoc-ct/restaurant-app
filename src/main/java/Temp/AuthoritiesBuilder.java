package Temp;

import com.ba.builder.UsernameBuilder;

public class AuthoritiesBuilder extends UsernameBuilder {
    private String authority;

    public AuthoritiesBuilder authority(String authority){
        this.authority=authority;
        return this;
    }
    public AuthoritiesBuilder username(String username){
        this.setUsername(username);
        return this;
    }

    @Override
    public Authorities build() {
        Authorities authorities= new Authorities();
        authorities.setUsername(this.getUsername());
        authorities.setAuthority(this.authority);
        return authorities;
    }
}
