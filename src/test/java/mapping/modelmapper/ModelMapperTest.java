package mapping.modelmapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import mapping.common.BaseTest;
import model.AbstractDTO;
import model.AbstractEntity;
import model.CatType;
import model.CatTypeDTO;
import model.Category;
import model.CategoryDTO;

public class ModelMapperTest extends BaseTest {

	protected ModelMapper getMapper() {
		ModelMapper modelMapper =  new ModelMapper();
		modelMapper.addMappings(new PropertyMap<CategoryDTO, Category>() {
			  protected void configure() {
			    skip().setVersion(0);
			  }
			});
			
		modelMapper.addMappings(new PropertyMap<CatTypeDTO, CatType>() {
			  protected void configure() {
			    skip().setVersion(0);
			  }
			});
		
		modelMapper.addConverter(new AbstractConverter<Category, AbstractDTO>() {
			@Override
			protected AbstractDTO convert(Category source) {
				if (source == null) return null;
				AbstractDTO dto = new AbstractDTO();
				dto.setUid(source.getUid());
				dto.setName(source.getName());
				return dto;
			}
		});


		modelMapper.addConverter(new Converter<HashMap, AbstractEntity>() {
			public Category convert(MappingContext<HashMap, AbstractEntity> context) {
				return null;
			}
		});

		modelMapper.addConverter(new AbstractConverter<Date, String>() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");

			@Override
			protected String convert(Date source) {
				if (source == null)	return null;
				return format.format(source);
			}
		});
		modelMapper.addConverter(new AbstractConverter<String, Date>() {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
			@Override
			protected Date convert(String source) {
				if (source == null) return null;
				try {
					return format.parse(source);
				}catch(ParseException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});		
		
		return modelMapper;
	}
	
	protected CategoryDTO toDto(Category source) {
		return getMapper().map(source, CategoryDTO.class);
	}
	
	protected Category fromDto(CategoryDTO source) {
		return getMapper().map(source, Category.class);
	}
	
	protected void fromDto(CategoryDTO source, Category destination) {
		getMapper().map(source, destination);
	}

	//@Override
	protected <S, D> D convert(S s, Class<D> d) {
		return getMapper().map(s, d);
	}

	//@Override
	protected <S, D> D convert(S s, D d) {
		getMapper().map(s, d);
		return d;
	}	
}