

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PublicSession;
import repositories.PublicSessionRepository;


@Component
@Transactional
public class StringToPublicSessionConverter implements Converter<String, PublicSession> {

	@Autowired
	PublicSessionRepository publicSessionRepository;

	@Override
	public PublicSession convert(String text) {
		PublicSession result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = publicSessionRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
