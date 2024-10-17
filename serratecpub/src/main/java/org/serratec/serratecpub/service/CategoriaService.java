package org.serratec.serratecpub.service;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.CategoriaDto;
import org.serratec.serratecpub.model.Categoria;
import org.serratec.serratecpub.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired

    private CategoriaRepository categoriaRepository;

    public List<CategoriaDto> obterTodasCategorias(){
        return categoriaRepository.findAll().stream().map(c -> CategoriaDto.toDto(c)).toList();
    }

    public Optional<CategoriaDto> obterCategoriaPorId(Long id){
        if (!categoriaRepository.existsById(id)){
            return Optional.empty();
        }
        return Optional.of(CategoriaDto.toDto(categoriaRepository.findById(id).get()));
    }

    public CategoriaDto salvarCategoria(CategoriaDto categoriaDto){
        Categoria categoriaEntity = categoriaRepository.save(categoriaDto.toEntity());
        return CategoriaDto.toDto(categoriaEntity);
    }

    public boolean excluirCategoria(Long id){
        if (!categoriaRepository.existsById(id)){
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }

    public Optional<CategoriaDto> alterarCategoria(Long id, CategoriaDto categoriaDto){
        if (!categoriaRepository.existsById(id)){
            return Optional.empty();
        }

        Categoria categoriaEntity = categoriaDto.toEntity();
        categoriaEntity.setId(id);
        categoriaRepository.save(categoriaEntity);
        return Optional.of(CategoriaDto.toDto(categoriaEntity));
    
    }
}
