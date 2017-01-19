# Euler Problem 5
$res = 1
$n = 20
(2..$n).each{|i| if $res % i != 0 then $res *= i/$res.gcd(i)end}
puts $res
