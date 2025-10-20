bật k8s trong docker desktop 

build file jar của source code:
`mvn clean package -DskipTests`

build image của app:
`docker build -t spring-app:latest .`

sau khi build xong thì chạy các file deploy của app và mysql:
`kubectl apply -f mysql-pvc.yaml`
`kubectl apply -f deployment-mysql.yaml`
`kubectl apply -f service-mysql.yaml`

`kubectl apply -f deployment.yaml`
`kubectl apply -f service.yaml`

sau khi deploy xong truy cập vào localhost:30080
![img_1.png](img_1.png)

tạo 1 card
![img_2.png](img_2.png)
![img_3.png](img_3.png)

xóa thử pod đang chạy mysql, k8s sẽ tự động tạo pod mới
![img_4.png](img_4.png)

và dữ liệu cũ vẫn được luu
![img_5.png](img_5.png)