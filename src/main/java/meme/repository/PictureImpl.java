package meme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import meme.models.Picture;



public interface PictureImpl extends JpaRepository<Picture, Integer> {

}
