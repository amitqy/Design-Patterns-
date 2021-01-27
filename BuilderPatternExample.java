package builder;

/*
When there are too many arguments in the constructor there is no need to maintain the
order, this design pattern take cares of that case. Order only need to maintained on
required parameters.

Sometimes its not necessary to put all parameters in the constructor, in that case
we put null or false( generally what we do) for each parameter that is not required. This design pattern also handles this case. We need not give
value for each parameter, it takes care of that case.
 */

class Vehicle{
    //required parameters
    private String engine;
    private int wheel;

    //optional parameter
    private int airbags;

    public  String getEngine(){
        return this.engine;
    }
    public int getAirbags(){
        return this.airbags;
    }
    public int getWheel() {
        return this.wheel;
    }
    /*
    The main class private constructor so that object can only be created via the
    builder class
    The main class only has getters.
     */
    private Vehicle(VehicleBuilder builder){
        this.engine = builder.engine;
        this.wheel = builder.wheel;
        this.airbags = builder.airbags;
    }

    /*
    Static inner class
     */
    public static class VehicleBuilder{
        private String engine;
        private int wheel;

        private int airbags;

        // public constructor with all required parameters
        public VehicleBuilder(String engine,int wheel){
            this.engine = engine;
            this.wheel = wheel;
        }

        /*
        For optional parameter we have the setter method
        we can set the optional parameter through this method if there is any requirement
        Setter method returns a builder object.
         */
        public VehicleBuilder setAirbags(int airbags){
            this.airbags = airbags;
            return this;
        }
        /*
        It returns object of the vehicle class after build method is called
         */
        public Vehicle build(){
            return new Vehicle(this);
        }
    }
}


public class BuilderPatternExample {
    public static void main(String[] args) {
        Vehicle car = new Vehicle.VehicleBuilder("1500cc", 4).setAirbags(4).build();
        Vehicle bike = new Vehicle.VehicleBuilder("250cc", 2).build();
        System.out.println(car.getEngine());
        System.out.println(car.getWheel());
        System.out.println(car.getAirbags());

        System.out.println(bike.getEngine());
        System.out.println(bike.getWheel());
        System.out.println(bike.getAirbags());
    }
}
