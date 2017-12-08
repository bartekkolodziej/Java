public class EmptyBoundingBox extends  Exception{
    EmptyBoundingBox(){}
    EmptyBoundingBox(String error){
        super(error);
    }
}
