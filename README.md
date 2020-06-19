# mn-cities
1. createdb -U postgres geodata
2. psql -U postgres -d geodata -c "create extension postgis"
3. gradle run
4. open http://localhost:8080/city/list
5. curl -X POST -d '{"name":"Melbourne","country":"US","population":76068,"capitol":false,"longitude": -80.633333,"latitude":28.116667,"location":"POINT (-80.633333 28.116667)"}' http:/localhost:8080/city/create -H "Content-type: application/json"
