# From Henri Cohen's "A Course in Computational Algebraic Number
# Theory".

# Algorithm 1.7.1 (Integer Square Root) Given a positive integer n,
# this algorithm computes the integer part of the square root of n,
# i.e. the number m such that m^2 <= n < (m + 1)^2.
  def isqrt(n)
    if n < 1 then return 0 end
    x = n
    loop do
      y = ((x + n/x)/2)
      if y < x
        x = y
      else
        return x
      end
    end
  end
=begin

# Cache the squares modulus 11, 63, 64, and 65. This is used to check
# for non-squares, since a square is a square mod k for all k. The
# choice of these numbers is based on the probability that a non-square
# is a square mod any of them, which is 6/715, less than a 1%.
  $squares = {}
  [11, 63, 64, 65].each do |m|
    $squares[m] = [false] * m
    (0...(m/2)).each {|i| $squares[m][i**2 % m] = true}
  end
=end


# Algorithm 1.7.3 (Square Test). Given a positive integer n,
# this algorithm determines whether n is a square or not,
# and if it is, outputs the square root of n. We assume the
# precomputations made above.
  def issquare(n)
=begin
    return false unless $squares[64][n % 64]

    r = n % 45045 # 45045 = 63*65*11
    return false unless $squares[63][r % 63]
    return false unless $squares[65][r % 65]
    return false unless $squares[11][r % 11]
=end

    q = isqrt(n)
    return q**2 == n ? q : false
end

class Integer
  def issquare?
    return issquare(self)
  end
end