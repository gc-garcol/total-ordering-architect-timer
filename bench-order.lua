-- wrk -t32 -c400 -d30s --latency -s bench-order.lua http://localhost:8080/api/order-book/buy-order

wrk.method = "POST"
wrk.headers["Content-Type"] = "application/json"
wrk.body = [[
{
  "postId": 1,
  "ownerId": 200,
  "amount": 10,
  "timeoutSecond": 10
}
]]
