<h1 align="center">Project Iot Distributed System</h1>

## 💻 Descrição

Aplicação ilustrar como e feito a utilização de kafka.

## 🧪 Tecnologias utilizadas

Ferramentas necessarias para exercutar o projeto

- Docker
- Java (JDK 11)
- Spring Boot
- Kafka
- Mavem
- IDE (vs code)
- Git

## ⚙ Projeto

Clone o repositório do projeto

```
git clone https://github.com/erikbernard/iot_distributed_system.git
```

Acesse a pasta do projeto

```
cd ./iot_distributed_system
```

Certifique que as dependências do file pom.xml estão instaladas.

## Execução do kafka com docker-compose

acesse a pasta do src e digite o comando

```
cd ./src

docker-compose up
```

Verifique se kafdrop está funcionando

```
http://localhost:9000/
```

Com o kafdrop podemos visualizar informações de brokers Kafka como tópicos existentes, os consumers, as mensagens enviadas a um tópico, como também criar partições.


## Executando o Back End Spring Boot

acesse a class "DistributedSystemApplication" execute a main.

GET: http://localhost:8080/kafka/publish?data=5

```
Successfully publisher
```

GET: http://localhost:8080/kafka/sensor
```
//body
[
    {
        "sensorName": "Forno - Room B",
        "macAddress": "EB:3F:BF:51:5B:CB",
        "minValue": 11.17,
        "maxValue": 98.59,
        "averageValue": 60.109000000000016,
        "location": "Kitchen",
        "date": "2023-12-10T13:49:50.715+00:00"
    }
]
```

POST: http://localhost:8080/kafka/sensor

```
// body
{
    "sensorName": "Forno - Room B",
    "macAddress": "EB:3F:BF:51:5B:CB",
    "minValue": 11.17,
    "maxValue": 98.59,
    "averageValue": 60.109000000000016,
    "location": "Kitchen",
    "date": "2023-12-10T13:49:50.715+00:00"
}

```

## Executando o Front End 

Abrar um novo terminal 

## Entra na pasta

```
cd front_end_iot
```

## Instalar depêndencias

```
npm i
```

## Executar o Front End

```
npm run dev
```
