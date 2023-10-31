# Spring Cloud Practice Project

本專案為 Spring Cloud 的練習專案，使用 Docker Compose 建立微服務的環境。
本 Repository 搭配筆記可於 [Codeniz - Blog](https://codeniz.com) 中找到

核心的主要內容會建置在 docker-compose-eureka.yml 中，其餘的 docker-compose yml 則是其他較小獨立的議題。

# 實作包含的內容

全篇採用的框架為 Springboot 3.0.6，語言為 Kotlin 1.8.21
由於 Netflix 廢棄了很多原有的框架，已經不支援的不會於此出現。

1. 服務註冊中心：Eureka、Consul、Zookeeper
2. 負載均衡：OpenFeign
3. 降級、熔斷、限流：resilience4j
4. Gateway：Spring Cloud Gateway
5. 服務設定：Spring Cloud Config
   - 配合自建 gitlab 演示
6. Spring Cloud Bus
7. Message Queue：Spring Cloud Stream
8. 服務監控：Spring Cloud Sleuth、Micrometer Tracing