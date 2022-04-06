package com.n2014758030.main.service;

import com.n2014758030.main.domain.Profile;
import com.n2014758030.main.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public List<Profile> findProfileByAll() {
        return profileRepository.findAll();
    }

    public Profile findProfileByIdx(Long idx) {
        return profileRepository.findById(idx).orElse(new Profile());
    }

    public void updateProfile(Profile profile) {
        profile = profileRepository.save(profile);
    }

    public void deleteProfile(Profile profile) {
        profileRepository.delete(profile);
    }

}
