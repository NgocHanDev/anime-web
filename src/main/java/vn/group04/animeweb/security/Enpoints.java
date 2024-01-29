package vn.group04.animeweb.security;

public class Enpoints {
    public static final String[] PUBLIC_GET_ENPOINTS ={"/the-loai", "/the-loai/**" , "/user/reset-password","/anime", "/anime/**", "binh-luan", "binh-luan/**","/danh-sach-yeu-thich", "/danh-sach-yeu-thich/**","/nguoi-dung/*" };
    public static final String[] PUBLIC_POST_ENPOINTS = {"/user/change-password" ,"/user/login", "/user/signup", "binh-luan", "/danh-sach-yeu-thich", "/util", "/util/**"};
    public static final String[] PUBLIC_PATCH_ENPOINTS = {"binh-luan/**", "/danh-sach-yeu-thich/**"};
    public static final String[] PUBLIC_DELETE_ENPOINTS = {"binh-luan/**","/user/danh-sach-yeu-thich"};
    public static final String[] ADMIN_ENPOINTS = {"/**"};
}
