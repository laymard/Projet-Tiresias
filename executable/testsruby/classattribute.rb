class George
  attr_accessor :name
  def init(name)
    @name = name
  end
  def print_name
    puts("Hi #{@name}")
  end
end

n = George.new
n.init("Walter White")
n.print_name