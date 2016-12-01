class String
  def palindrome?
    self == self.reverse
  end
end

def splitspace(data)
  data.each_line.map{|line| line.split(/\s+/)}
end

def divisors_of(num)
  tmp = Math.sqrt(num)
  if num == 1 then return [1] end
  return (1..tmp.ceil-1).map{|i| if num % i == 0 then [i,num/i] else [] end}.push(if (tmp%1).zero? then [tmp.to_i] else [] end).flatten!
end
module FrozenCacher
  def FrozenCacher.fcache
    @frozen_cache ||= {}
  end

  def fcache
    FrozenCacher.fcache[self] ||= {}
  end
end
class Integer
  include FrozenCacher
  require './sqrttest'
  def fact
    fcache[:fact] ||= ((1..self).reduce(:*) || 1)
  end

  def pentagonal?
    fcache[:pentagonal] ||= (Math.sqrt(24*self+1) + 1) % 6 == 0
  end

  # n^2 + n - 2T = 0
  def triangular?
    fcache[:triangular] ||= (Math.sqrt(8*self +1)-1)%2 == 0
  end

  # 2n^2 - n - H = 0
  def hexagonal?
    fcache[:hexagonal] ||= (Math.sqrt(8*self +1)+1)%4 == 0
  end

  # 5n^2 - 3n - 2H = 0
  def heptagonal?
    fcache[:heptagonal] ||= (Math.sqrt(40*self +9)+3)%10 == 0
  end

  # 3n^2 -2n - O = 0
  def octagonal?
    fcache[:octagonal] ||= (Math.sqrt(3*self +1)+1)% 3 == 0
  end
  def iscube?
    fcache[:cube] ||= icubert(self)**3 == self
  end
end

@triangle_gen = Enumerator.new do |yielder|
  i = 1
  tmp = 1
  loop do
    i = i+1
    tmp = tmp+i
    yielder.yield tmp
  end
end

@hexagon_gen = Enumerator.new do |yielder|
  i = 1
  tmp = 1
  loop do
    i = i+4
    tmp = tmp+i
    yielder.yield tmp
  end
end

@octagon_gen = Enumerator.new do |yielder|
  i = 1
  tmp = 1
  loop do
    i = i+6
    tmp = tmp+i
    yielder.yield tmp
  end
end

# x^3-n = 0, y = x - (x^3-n)/3x^2 = (x*2/3 + n/3x^2)
def icubert(n)
  x = n
  loop do
    y = ((2*x + n/(x**2))/3)
    if y < x
      x = y
    else
      return x
    end
  end
end

# Hashtag Farey <3 to antiaverage
def coprime_gen(n)
  return Enumerator.new do |yielder|
    a = 0
    b = 1
    c = 1
    d = n
    yielder.yield [a,b]
    while c <= n
      k = ((n+b).to_f/d).floor
      a,b,c,d = c,d,k*c-a, k*d-b
      yielder.yield [a,b]
    end
  end
end

def factors_of(n, m=2)
  a = [[n]]
  s = (m..Math.sqrt(n))
  t = s.select{|i|n%i<1}
  t.each do |d|
    factors_of(n/d,d).each do |j|
      a.push([d]+j)
    end
  end
  return a
end
