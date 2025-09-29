API có sẵn tài khoản admin là (admin | 123)

đăng kí, đăng nhập sẽ cho phép tất cả người dùng

đăng kí tk (user | 123)
![img.png](img.png)

sau khi login với tk user vừa đki thì đc token jwt
![img_1.png](img_1.png)

sau khi đăng nhập sẽ sử dụng token để dùng các API
với header Authorization : Bearer "....token...."

Tạo blog dùng của user
![img_2.png](img_2.png)
kiểm tra blogs của user vừa tạo
![img_3.png](img_3.png)

Nếu dùng token của user để xóa user khác thì sẽ bị FORBIDDEN
![img_4.png](img_4.png)

Các tài khoản đang có :
![img_6.png](img_6.png)

Đăng nhập với tk admin | 123
![img_5.png](img_5.png)

Xóa tài khoản user với token của admin
![img_7.png](img_7.png)

danh sách tài khoản sau khi xóa :
![img_8.png](img_8.png)

