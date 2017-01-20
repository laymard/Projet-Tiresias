class George
  attr_reader :name
  def print_name
    puts("Hi #{@name}")
  end
end

n = George.new
n.name = "Walter White" # should raise error
