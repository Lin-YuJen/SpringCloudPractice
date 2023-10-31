# 註冊中心的比較

|           | Language | CAP | Health Check | Exposed Port | Spring Cloud Supported |
|-----------|----------|-----|--------------|--------------|------------------------|
| Eureka    | Java     | AP  | Supported    | HTTP         | Yes                    |
| Consul    | Go       | CP  | Yes          | HTTP/DNS     | Yes                    |
| Zookeeper | Java     | CP  | Yes          | Client       | Yes                    |

* CP：一致性+分區容錯，性能不高時用，沒有高擴展性
* AP：可用性+分區容錯，對一致性要求低的時候用
