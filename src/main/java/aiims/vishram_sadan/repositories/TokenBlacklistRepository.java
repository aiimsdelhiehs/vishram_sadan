package aiims.vishram_sadan.repositories;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class TokenBlacklistRepository {
	private Set<String> blacklist = new HashSet<>();

    public void addToken(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
