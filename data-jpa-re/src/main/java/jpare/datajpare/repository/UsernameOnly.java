package jpare.datajpare.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    @Value("#{target.username + ' ' + target.age}")
    String getUserdfsname();
}
