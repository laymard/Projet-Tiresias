def fact(n)
    if (n <= 1) then
        return 1
    end
    return n * fact(n-1)
end

puts(fact(3)) # should return 6
puts(fact(50)) # should return 30414093201713378043612608166064768844377641568960512000000000000
puts(fact(0)) # should return 1
