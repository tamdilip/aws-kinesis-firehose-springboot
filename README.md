# âµï¸aws-kinesis-firehose-springboot

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod&style=flat-square)](https://gitpod.io/#https://github.com/tamdilip/aws-kinesis-firehose-springboot) 

Simple Spring Boot project to stream data to S3 through Amazon Kinesis Firehose and query data via Athena with the schema crawled by Glue.

`Data` ðð» `Kinesis` ðð» `S3` ðð» `Glue` ðð» `Athena`

## Setup
Update AWS access key and secret key in `application.properties`.

```sh
        $ mvn spring-boot:run
```

## AWS Resources reference
### Kinesis firehose delivery
![Kinesis firehose delivery](https://raw.githubusercontent.com/tamdilip/aws-kinesis-firehose-springboot/main/src/main/resources/images/kinesis-s3.png)
### S3 Buffered data
![Kinesis firehose delivery](https://raw.githubusercontent.com/tamdilip/aws-kinesis-firehose-springboot/main/src/main/resources/images/s3.png)
### Glue schema crawler
![Kinesis firehose delivery](https://raw.githubusercontent.com/tamdilip/aws-kinesis-firehose-springboot/main/src/main/resources/images/Glue.png)
### Athena data query
![Kinesis firehose delivery](https://raw.githubusercontent.com/tamdilip/aws-kinesis-firehose-springboot/main/src/main/resources/images/Athena.png)

**Happy coding :) !!**
