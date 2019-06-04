# consul-sp
The service provider with consul

## mutipath registry
### Cluster for one DC
3 consul sercers 
3 consul clients
Service register and discovery through the 3 clients. In normal conditions, services register in one client.
But the mutipath model, Services will register in each clients of the 3.

> Notic: The Annotations '@EnableFeignClients' must be declared in ConsulSpApplication

