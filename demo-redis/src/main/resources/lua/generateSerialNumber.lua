--- 生成流水号lua脚本
--- 传入key 以及流水号最大上限，达到上限后，重置为1
if (redis.call("exists", KEYS[1]) == 1) then
    local index = tonumber(redis.call("get", KEYS[1]))
    local num = tonumber(ARGV[1])
    if (index <math.abs(num)) then
       redis.call("incrby", KEYS[1], 1);
       return   tonumber(redis.call("get", KEYS[1]));
    else
    	  redis.call("set", KEYS[1], 1)
       return  tonumber(redis.call("get", KEYS[1]));
    end;
else
    	  redis.call("set", KEYS[1], 1)
       return  tonumber(redis.call("get", KEYS[1]));
end;