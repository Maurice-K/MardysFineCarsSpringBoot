package ewkconsulting.software.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempCredentials {
	String username;
	String tmpPassword;
}
