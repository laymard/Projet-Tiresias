class George
  attr_reader :name
  def print_name
    puts("Hi #{@name}")
  end
end

n = George.new
begin
  n.name = "Walter White" # should raise error
rescue Exception => e
  puts("Error raised, as expected")
  puts(e.class.name)
end
