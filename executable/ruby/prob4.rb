require_relative 'utils'
$res = 0
$n = 1000
($n-1).downto(1) {|tmp| ($n-1).downto(1) {|tmp2| if tmp*tmp2 > $res and (tmp*tmp2).to_s.palindrome? then $res = tmp*tmp2 end}}
puts $res