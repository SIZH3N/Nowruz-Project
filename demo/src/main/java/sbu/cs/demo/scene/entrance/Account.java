package sbu.cs.demo.scene.entrance;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.security.PublicKey;
import java.util.List;

public class Account  {
    protected String name;
    protected int age;
    protected String email;
    protected String username;
    protected String password;
    protected String role;
    protected List<String> followedArtists;


    public Account(String name, int age, String email, String username, String password, String role , List<String> followedArtists) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.followedArtists = followedArtists;
    }

    public void displayProfile() {
        System.out.println("wrong profile");
    }

    public String getAccountname() {
        return name;
    }

    public String getAccountUsername() {
        return username;
    }

    public String getAccountRole() {
        return role;
    }

    public String getAccountPasword() {
        return password;
    }

}
