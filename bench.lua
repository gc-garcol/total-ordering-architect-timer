-- wrk -t32 -c400 -d30s --latency -s bench.lua http://localhost:8080/api/order-book/buy-post

wrk.method = "POST"
wrk.headers["Content-Type"] = "application/json"
wrk.body = [[
{
  "userId": 100,
  "amount": 10
}
]]
