# smart_pick
http GET http://localhost:8088/orders
http POST http://localhost:8088/orders productName="A향수" qty=1 customerId=10001 orderDate="20200617" status="ORDERED" storeId=90001 orderType="PICK"
http POST http://localhost:8088/orders productName="B신발" qty=2 customerId=10002 orderDate="20200617" status="ORDERED" storeId=90003 orderType="PICK"
http POST http://localhost:8088/orders productName="B신발" qty=2 customerId=10002 orderDate="20200617" status="ORDERED" storeId=90003 orderType="DELIVERY"
http POST http://localhost:8088/orders productName="C셔츠" qty=3 customerId=10008 orderDate="20200617" status="ORDERED" storeId=90004 orderType="PICK"

http PATCH http://localhost:8088/smartDeliveries/2 deliveryDate="20200617" status="DELIVERED"
http PATCH http://localhost:8088/smartDeliveries/3 deliveryDate="20200617" status="DELIVERED"

http PATCH http://localhost:8088/picks/2 deliveryDate="20200617" status="CONFIRMED"
http PATCH http://localhost:8088/picks/2 deliveryDate="20200617" status="PICKED"

http PATCH http://localhost:8088/orders/1 status="CANCELED"
http PATCH http://localhost:8088/orders/4 status="CANCELED"


http GET http://a6c022ea1caf54b1f8aecddeec081eb9-1554629413.ap-northeast-2.elb.amazonaws.com/orders
