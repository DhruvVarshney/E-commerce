These are the commands that are to be run :

create item : 

curl -X POST \
 http://localhost:8080/item \
 -H 'content-type: application/json' \
 -d '{
	"name":"Redmi Note Pro 5",
	"mrp":10000,
	"sellingPrice":9000,
	"quantity":10,
	"category":"phone",
	"image":"https://static.digit.in/product/e9ef5af124f135e07d77b97fb0ad70b407acd0c3.jpeg"
}'

update item : 

curl -X POST \
  http://localhost:8080/item/1 \
  -H 'content-type: application/json' \
  -d '{
	"sellingPrice":9500
}'


getItem :

curl -X GET http://localhost:8080/item/1 

deleteItem :

curl -X DELETE http://localhost:8080/item/1 







createUser ::

curl -X POST \
  http://localhost:8080/user \
  -H 'content-type: application/json' \
  -d '{
	"email":"test@test.com",
	"name":"HEllO"
}'

place order : 

curl -X POST \
  http://localhost:8080/order/place \
  -H 'content-type: application/json' \
  -d '{
	"emailId":"test@test.com",
	"amount":10000,
	"itemIdAndQuantity":{
		"1":1
	}
}'


get order by email : 

curl -X GET http://localhost:8080/order/emailId/test@test.com
