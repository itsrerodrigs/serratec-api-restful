package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.CategoriaDto;
import org.serratec.serratecpub.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired

    private CategoriaService categoriaService;

    @GetMapping
    public List <CategoriaDto> listarCategorias(){
        return categoriaService.obterTodasCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> listarCategoriaPorId(@PathVariable Long id){
        Optional<CategoriaDto> categoriaDto = categoriaService.obterCategoriaPorId(id);
        if(!categoriaDto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaDto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDto cadastrarCategoria(@RequestBody CategoriaDto categoriaDto){
        return categoriaService.salvarCategoria(categoriaDto);
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCategoria(@PathVariable Long id, CategoriaDto categoriaDto){
        if (!categoriaService.excluirCategoria(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
        }
        return ResponseEntity.ok("Categoria " + categoriaDto + " excluída com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> alterarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto){
        Optional<CategoriaDto> categoriaAlterada = categoriaService.alterarCategoria(id, categoriaDto);
        if (!categoriaAlterada.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaAlterada.get());
    }
}
