package mapping.orika;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import mapping.common.BaseTest;
import model.AbstractDTO;
import model.Category;
import model.CategoryDTO;
import model.CatTypeDTO;
import model.CatType;

public class OrikaTest extends BaseTest {

	public MapperFactory getMapper(boolean ignoreVersion) {
		MapperFactory mapper = new DefaultMapperFactory.Builder().build();
		
		ClassMapBuilder<Category, AbstractDTO> builder1 = mapper.classMap(Category.class, AbstractDTO.class);
		if (ignoreVersion) builder1.exclude("version");
		builder1.byDefault().register();
		
		ClassMapBuilder<Category, CategoryDTO> builder2 = mapper.classMap(Category.class, CategoryDTO.class);
		if (ignoreVersion) builder2.exclude("version");
		builder2.byDefault().register();

		ClassMapBuilder<CatType, CatTypeDTO> builder3 = mapper.classMap(CatType.class, CatTypeDTO.class);
		if (ignoreVersion) builder3.exclude("version");
		builder3.byDefault().register();
		
		ConverterFactory converterFactory = mapper.getConverterFactory();
		converterFactory.registerConverter(new BidirectionalConverter<Date, String>() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");

			public String convertTo(Date source, Type<String> destinationType) {
				if (source == null) return null;
				return format.format(source);
			}

			public Date convertFrom(String source, Type<Date> destinationType) {
				if (source == null) return null;
				try {
					return format.parse(source);
				} catch (ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});

		return mapper;
	}
	

	public MapperFacade getToFacade() {
		return getMapper(false).getMapperFacade();
	}
	
	public MapperFacade getFromFacade() {
		return getMapper(true).getMapperFacade();
	}
	
	@Override
	protected CategoryDTO toDto(Category source) {
		return getToFacade().map(source, CategoryDTO.class);
	}

	@Override
	protected Category fromDto(CategoryDTO source) {
		return getFromFacade().map(source, Category.class);
	}
	
	@Override
	protected void fromDto(CategoryDTO source, Category destination) {
		getFromFacade().map(source, destination);
	}
	
//	@Override
//	protected <S, D> D convert(S s, Class<D> d) {
//		return getMapper().map(s, d);
//	}
//
//	@Override
//	protected <S, D> D convert(S s, D d) {
//		getMapper().map(s, d);
//		return d;
//	}
}