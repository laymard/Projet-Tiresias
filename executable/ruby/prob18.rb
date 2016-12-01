DATA = $stdin.readlines

treated = DATA.map{|t| t.split.map{|tab| tab.to_i}}.reverse

res = treated.inject [] do |memo,t|
  if memo != [] then t = t.zip(memo).map{|x,y| x+y} end
  if memo.length != 1 then
    memo = []
    t.each_cons(2){|i,j| memo << if i < j then j else i end}
  else
    memo = t
  end
  memo
end
puts res