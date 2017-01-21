class George
  attr_writer :name
end

n = George.new
n.name = "Walter White"
begin
  puts n.name # should raise error
rescue Exception => e
  puts("Error raised, as expected")
  puts(e.message)
end
