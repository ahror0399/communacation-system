package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Branch;
import uz.developer.communication_system.entity.Packet;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.payload.PacketDto;
import uz.developer.communication_system.repository.BranchRepository;
import uz.developer.communication_system.repository.PacketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacketService {

    @Autowired
    private PacketRepository packetRepository;
    @Autowired
    private BranchRepository companyRepository;

    public List<Packet> getPackets(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Packet> packets = packetRepository.findAll(pageable);
        return packets.getContent();
    }
    public Packet getPacket(Integer id){
        Optional<Packet> optionalPacket
                = packetRepository.findById(id);
        return optionalPacket.orElse(null);
    }
    public ApiResponse savePacket(PacketDto packetDto){

        boolean exists = packetRepository.existsByPacketCode(packetDto.getPacketCode());
        if (exists)
            return new ApiResponse("such code already exists", false);

        Packet packet = new Packet();
        packet.setPacketType(packetDto.getPacketType());
        packet.setAmount(packetDto.getAmount());
        packet.setPrice(packetDto.getPrice());
        packet.setExpireDay(packetDto.getExpireDay());
        packet.setDescription(packetDto.getDescription());
        packet.setPacketCode(packetDto.getPacketCode());

        packetRepository.save(packet);
        return new ApiResponse("saved packet", true);
    }
    public  ApiResponse editPacket(Integer id, PacketDto packetDto){

        Optional<Packet> optionalPacket
                = packetRepository.findById(id);
        if (optionalPacket.isEmpty())
            return new ApiResponse("Error!! edit is not packet", false);

        boolean exists
                = packetRepository.existsByPacketCode(packetDto.getPacketCode());
        if (exists)
            return new ApiResponse("such code is available in the company",false);

        Packet packet = optionalPacket.get();
        packet.setPacketType(packetDto.getPacketType());
        packet.setAmount(packetDto.getAmount());
        packet.setPrice(packetDto.getPrice());
        packet.setExpireDay(packetDto.getExpireDay());
        packet.setDescription(packetDto.getDescription());
        packetRepository.save(packet);
        return new ApiResponse("edit packet",true);

    }
    public ApiResponse deletePacket(Integer id){
        try {
            packetRepository.deleteById(id);
            return new ApiResponse("delete packet", true);
        }catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }
}
