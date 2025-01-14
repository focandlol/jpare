package focandlol.simplecarjpa.dto;

import lombok.Data;

@Data
public class CarInputDto {
  private String modelName;
  private Long companyId;
  private Integer passengerCapacity;

}
