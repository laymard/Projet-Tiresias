def pascalTriangle(num)
  if num == 1 then return [1] end
  res = [1]
  (num-1).times do
    res = (res + [0]).zip([0] + res).map{|x,y| x+y}
  end
  return res
end
puts pascalTriangle(31)[15]