class George
  attr_accessor :name
  def print_name
    puts("Hi #{@name}")
  end
end

n = George.new
n.name = "Walter White"
n.print_name