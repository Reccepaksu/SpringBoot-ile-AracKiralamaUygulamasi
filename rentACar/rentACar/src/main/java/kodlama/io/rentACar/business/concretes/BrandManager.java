package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.brandRepository;
import kodlama.io.rentACar.entities.concretes.brand;
import lombok.AllArgsConstructor;

@Service //bu sınıf bir business nesnesidir.
@AllArgsConstructor
public class BrandManager implements BrandService{
	
	private brandRepository BrandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;

	@Override
	public List<GetAllBrandsResponse> getAll() {
		
		List<brand> brands = BrandRepository.findAll();
	
	
		List<GetAllBrandsResponse> brandsResponse = brands.stream().map(brand->this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		
		brand brands =this.modelMapperService.forRequest().map(createBrandRequest, brand.class);
		
		// TODO Auto-generated method stub
		this.BrandRepository.save(brands);
	}

	@Override
	public GetByIdBrandResponse getById(int id) {
		brand brand = this.BrandRepository.findById(id).orElseThrow();
		
		GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		return response;
	}

	@Override
	public void update(UpdateBrandRequest ubdateBrandRequest) {
		
		brand brands =this.modelMapperService.forRequest().map(ubdateBrandRequest, brand.class);
		this.BrandRepository.save(brands);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		this.BrandRepository.deleteById(id);
		
	}
	



}
