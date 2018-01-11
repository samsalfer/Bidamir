

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Author;
import repositories.AuthorRepository;


@Component
@Transactional
public class StringToAuthorConverter implements Converter<String, Author> {

	@Autowired
	AuthorRepository AuthorRepository;

	@Override
	public Author convert(String text) {
		Author result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = AuthorRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
